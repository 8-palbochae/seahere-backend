delete from outgoing_detail;
delete from outgoing;
delete from inventory_detail;
delete from inventories;
delete from company;
delete from users;
delete from product;


INSERT INTO company (company_id) values (1);
insert into USERS(user_id,username) values(1,'아리랑'),
                                          (2,'스리랑');
insert into product(product_id,product_name,qr,product_img) values (1,'광어','',''),
                                                                   (2,'넙치','',''),
                                                                   (3,'갈치','',''),
                                                                   (4,'고등어','','');
INSERT INTO outgoing (outgoing_id, company_id, outgoing_date, outgoing_state, partial_outgoing,user_id) VALUES
                                                                                                                  (1, 1, '2024-07-27', 'PENDING', true,2),
                                                                                                                  (2, 1, '2024-07-27', 'PENDING', true,2);
INSERT INTO outgoing_detail (detail_id, price, product_id, outgoing_id, quantity,state,category,country,natural_status) values (1,10000,1,1,20,'ACTIVE','활어','국산','양식'),
                                                                                           (2,10000,2,1,20,'ACTIVE','활어','국산','양식'),
                                                                                           (3,10000,3,1,20,'ACTIVE','활어','국산','양식'),
                                                                                           (4,10000,4,1,20,'ACTIVE','활어','국산','양식'),
                                                                                                 (5,10000,1,1,20,'DELETE','활어','국산','양식'),
                                                                                                 (6,10000,1,2,20,'DELETE','활어','국산','양식'),
                                                                                                 (7,10000,2,2,20,'DELETE','활어','국산','양식'),
                                                                                                 (8,10000,3,2,20,'DELETE','활어','국산','양식'),
                                                                                                 (9,10000,4,2,20,'DELETE','활어','국산','양식'),
                                                                                                 (10,10000,1,2,20,'DELETE','활어','국산','양식');
insert into INVENTORIES (inventory_id, category, country, natural_status, incoming_date, product_id, quantity, company_id)
values (1,'활어','국산','양식','2024-07-08',1,150,1),
       (2,'활어','국산','양식','2024-07-08',2,160,1),
       (3,'활어','국산','양식','2024-07-08',3,170,1),
       (4,'선어','국산','양식','2024-07-08',4,400,1);
