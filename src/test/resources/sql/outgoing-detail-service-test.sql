delete from outgoing_detail;
delete from outgoing;
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
INSERT INTO outgoing_detail (detail_id, price, product_id, outgoing_id, quantity,state) values (1,10000,1,1,20,'ACTIVE'),
                                                                                           (2,10000,2,1,20,'ACTIVE'),
                                                                                           (3,10000,3,1,20,'ACTIVE'),
                                                                                           (4,10000,4,1,20,'ACTIVE'),
                                                                                                 (5,10000,1,1,20,'DELETE'),
                                                                                                 (6,10000,1,2,20,'DELETE'),
                                                                                                 (7,10000,2,2,20,'DELETE'),
                                                                                                 (8,10000,3,2,20,'DELETE'),
                                                                                                 (9,10000,4,2,20,'DELETE'),
                                                                                                 (10,10000,1,2,20,'DELETE');