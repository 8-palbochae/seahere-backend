delete from outgoing_detail;
delete from outgoing;
delete from inventories;
delete from company;
delete from users;
INSERT INTO company (company_id) values (1);
insert into USERS(user_id,username) values(1,'아리랑'),
                                          (2,'스리랑');

INSERT INTO outgoing (outgoing_id, company_id, outgoing_date, outgoing_state, partial_outgoing,user_id) VALUES
    (1, 1, '2024-7-20', 'PENDING', true,1),
    (2, 1, '2024-7-28', 'READY', true,2),
    (3, 1, '2024-7-28', 'PENDING', true,2),
    (4, 1, '2024-7-28', 'PENDING', true,2),
    (5, 1, '2024-7-28', 'READY', true,2);

INSERT INTO outgoing_detail (detail_id, price, product_name, outgoing_id, quantity) values (1,10000,'광어',1,20),
                                                                                           (2,10000,'광어',3,20);