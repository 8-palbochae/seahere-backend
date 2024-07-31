delete from outgoing_detail;
delete from outgoing;
delete from company;
delete from users;


INSERT INTO company (company_id) values (1);
insert into USERS(user_id,username) values(1,'아리랑'),
                                          (2,'스리랑');
INSERT INTO outgoing (outgoing_id, company_id, outgoing_date, outgoing_state, partial_outgoing,user_id) VALUES
                                                                                                                  (1, 1, '2024-07-27', 'PENDING', true,2),
                                                                                                                  (2, 1, '2024-07-27', 'PENDING', true,2);
INSERT INTO outgoing_detail (detail_id, price, product_name, outgoing_id, quantity,state) values (1,10000,'광어',1,20,'ACTIVE'),
                                                                                           (2,10000,'넙치',1,20,'ACTIVE'),
                                                                                           (3,10000,'갈치',1,20,'ACTIVE'),
                                                                                           (4,10000,'고등어',1,20,'ACTIVE'),
                                                                                                 (5,10000,'광어',1,20,'DELETE'),
                                                                                                 (6,10000,'광어',2,20,'DELETE'),
                                                                                                 (7,10000,'넙치',2,20,'DELETE'),
                                                                                                 (8,10000,'갈치',2,20,'DELETE'),
                                                                                                 (9,10000,'고등어',2,20,'DELETE'),
                                                                                                 (10,10000,'광어',2,20,'DELETE');