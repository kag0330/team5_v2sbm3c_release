SELECT * from shoes;

SELECT shoesno, title
FROM shoes
WHERE visible = 'Y';


select p.paymentno, p.rdate
from payment p
INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
INNER JOIN options o          ON o.optionno = pd.optionno
INNER JOIN shoes s            ON s.shoesno = o.shoesno
INNER JOIN category_list cl   ON cl.shoesno = s.shoesno
INNER JOIN category c         ON c.categoryno = cl.categoryno
WHERE p.cs_status IS NULL;

SELECT DISTINCT s.shoesno
from payment p
INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
INNER JOIN options o          ON o.optionno = pd.optionno
INNER JOIN shoes s            ON s.shoesno = o.shoesno
INNER JOIN category_list cl   ON cl.shoesno = s.shoesno
INNER JOIN category c         ON c.categoryno = cl.categoryno
INNER JOIN member m           ON m.memberno = p.memberno
WHERE p.cs_status IS NULL AND m.memberno = 16;




select loginno, ip, rdate, memberno from login_history;

SELECT
  day,
  COUNT(DISTINCT memberno) AS member_count
FROM (
  SELECT
    memberno,
    TO_CHAR(rdate, 'YYYY-MM-DD') AS day
  FROM
    login_history
  GROUP BY
    memberno,
    TO_CHAR(rdate, 'YYYY-MM-DD')
)
GROUP BY
  day
ORDER BY
  day;









select * from payment;