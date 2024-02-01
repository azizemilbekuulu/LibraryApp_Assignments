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

select * from books;
select * from book_categories;

select b.name as book_name, b.isbn, b.year, b.author, bc.name as category_name, b.description from books b
    join book_categories bc on b.book_category_id = bc.id
where b.name = 'Clean Code';

select borrowed_



