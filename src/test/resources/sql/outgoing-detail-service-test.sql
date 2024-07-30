INSERT INTO company (company_id) values (1);
INSERT INTO outgoing (outgoing_id, company_id, outgoing_date, outgoing_state, partial_outgoing,customer_name) VALUES
                                                                                                                  (1, 1, '2024-07-27', 'PENDING', true,'스리랑');
INSERT INTO outgoing_detail (detail_id, price, product_name, outgoing_id, quantity) values (1,10000,'광어',1,20),
                                                                                           (2,10000,'넙치',1,20),
                                                                                           (3,10000,'갈치',1,20),
                                                                                           (4,10000,'고등어',1,20);