-- View Movie List Admin (MOVC001)
SELECT * FROM moviecruiser.movie_list;

-- View Menu Item List Customer (MOVC002)
SELECT * FROM moviecruiser.movie_list where mo_active='1' and mo_date_of_launch < (select(CURDATE()));

--  Edit Movie List (MOVC003)
select * from moviecruiser.movie_list where mo_id='1';


update moviecruiser.movie_list
 set 
mo_title='New Movie',
mo_gross='235659755',
mo_active='1',
mo_date_of_launch='2022-02-21',
mo_genre='Romance',
me_has_teaser='1' 
where mo_id='1';

-- Add to Favorites (MOVC004)
INSERT INTO `moviecruiser`.`favorites` (`fv_us_id`, `fv_mo_id`) VALUES ('1', '1');
INSERT INTO `moviecruiser`.`favorites` (`fv_us_id`, `fv_mo_id`) VALUES ('1', '3');
INSERT INTO `moviecruiser`.`favorites` (`fv_us_id`, `fv_mo_id`) VALUES ('1', '2');

-- View Favorites (MOVC005)
SELECT mo_title,mo_genre,mo_date_of_launch,mo_has_teaser FROM moviecruiser.movie_list
inner join moviecruiser.favorites on fv_mo_id = mo_id
where fv_us_id=1;

SELECT count(mo_id) as Total_Favorites FROM moviecruiser.movie_list
inner join moviecruiser.favorites on fv_mo_id = mo_id
where fv_us_id=1;

-- Remove Movie from Favorites (MOVC006)
delete from moviecruiser.favorites where fv_us_id=1 and fv_mo_id=4 limit 1;

