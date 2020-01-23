DELIMITER $$
CREATE PROCEDURE usp_get_book_count_by_author(IN first_name VARCHAR(50), IN last_name VARCHAR(50), OUT result INT)
BEGIN
  SET result = (SELECT count(*)
  FROM authors as a
  JOIN books as b
  ON b.author_id = a.id
  WHERE a.first_name = first_name
  AND a.last_name = last_name);
END $$

DELIMITER $$
CREATE PROCEDURE udp_find_books_by_author(IN first_name VARCHAR(255), IN last_name VARCHAR(255), OUT books_count INT)
  BEGIN
    SET books_count = (SELECT COUNT(*) AS books
                       FROM
                         `books` AS b
                         JOIN
                         `authors` AS a ON b.author_id = a.id
                       WHERE
                         a.first_name = first_name
                         AND a.last_name = last_name
                       GROUP BY a.id);
  END $$
DELIMITER ;

SET @answer = 0;
CALL udp_find_books_by_author('Amanda', 'Rice', @answer);
SELECT @answer;

SELECT count(*)
FROM authors as a
JOIN books as b
ON b.author_id = a.id
WHERE a.first_name = 'Wanda'
AND a.last_name = 'Morales';

SELECT count(*)
FROM books
where author_id = 26;