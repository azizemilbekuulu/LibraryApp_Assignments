SELECT *
FROM books;

-- US 01 -1
-- OPT 1
select id
from users;
-- getColumnDataAsList --> List --> size --> 10
-- getColumnDataAsList --> Set  --> size --> 10

-- OPT 2
select count(id)
from users; -- 5277          --> ACTUAL
select count(distinct id)
from users;
-- 5277 --> EXPECTED


-- .....
-- USER STORY 2----------
  select count(*) from books;


-- USER STORY 3-----
select id as i, parent_id as pi, name as n, description as d, extra_data as ed
from book_categories;

select name from book_categories;

