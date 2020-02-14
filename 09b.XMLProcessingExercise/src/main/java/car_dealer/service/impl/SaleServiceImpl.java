package car_dealer.service.impl;

import car_dealer.domain.dto.exportDTO.SaleWithDiscountDTO;
import car_dealer.domain.dto.exportDTO.SaleWithDiscountRootDTO;
import car_dealer.domain.entity.Car;
import car_dealer.domain.entity.Customer;
import car_dealer.domain.entity.Part;
import car_dealer.domain.entity.Sale;
import car_dealer.repository.CarRepository;
import car_dealer.repository.CustomerRepository;
import car_dealer.repository.SaleRepository;
import car_dealer.service.api.SaleService;
import car_dealer.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private static final String SALES_WITH_DETAILS_XML_EXPORT_PATH = "src/main/resources/xml/output/sales-with-details.xml";

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;
    private final XMLParser xmlParser;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper mapper, XMLParser xmlParser) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedMultipleSales() {
        if (this.saleRepository.count() == 0) {
            List<Car> allCars = this.carRepository.findAll();
            List<Customer> allCustomers = this.customerRepository.findAll();
            Double[] discounts = {0.0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};
            Random random = new Random();

            for (Car car : allCars) {
                Sale sale = new Sale();
                sale.setCar(car);
                sale.setCustomer(allCustomers.get(random.nextInt(allCustomers.size())));
                sale.setDiscount(discounts[random.nextInt(discounts.length)]);
                this.saleRepository.save(sale);
            }
        }
    }

    @Override
    public SaleWithDiscountRootDTO getAllSalesWithDetails() {
        SaleWithDiscountRootDTO saleWithDiscountRootDTO = new SaleWithDiscountRootDTO();

        List<SaleWithDiscountDTO> sales = this.saleRepository.getAllSalesWithDetails()
                .stream()
                .map(s -> {
                    SaleWithDiscountDTO sale = this.mapper.map(s, SaleWithDiscountDTO.class);

                    BigDecimal price = s.getCar().getParts()
                            .stream()
                            .map(Part::getPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    sale.setPrice(price);

                    sale.setPriceWithDiscount(price.subtract(price.multiply(BigDecimal.valueOf(sale.getDiscount()))));

                    return sale;
                })
                .collect(Collectors.toList());

        saleWithDiscountRootDTO.setSales(sales);

        return saleWithDiscountRootDTO;
    }

    @Override
    public void exportSalesWithDetails() {
        this.xmlParser.toXML(this.getAllSalesWithDetails(), SALES_WITH_DETAILS_XML_EXPORT_PATH);
    }
}
