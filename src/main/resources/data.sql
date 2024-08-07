DELETE FROM inventories;
DELETE FROM company;
DELETE FROM product;
-- product 테이블 데이터
INSERT INTO product (product_id, product_name, qr, product_img) VALUES
                                                                    (1, '광어', 'qr1', 'img1'),
                                                                    (2, '우럭', 'qr2', 'img2'),
                                                                    (3, '숭어', 'qr3', 'img3'),
                                                                    (4, '민어', 'qr4', 'img4'),
                                                                    (5, '장어', 'qr5', 'img5'),
                                                                    (6, '문어', 'qr6', 'img6');

insert into USERS(user_id,username) values(1,'스리랑'),
                                          (2,'아리랑');
INSERT INTO company(company_id) VALUES (101);
INSERT INTO company(company_id) VALUES (102);
INSERT INTO company(company_id) VALUES (1);

INSERT INTO company(company_id) VALUES (4);
INSERT INTO USERS(user_id) VALUES (5);

INSERT INTO incoming(incoming_id,category,country,country_detail,incoming_date,incoming_price,memo,natural_status,quantity,company_id,product_id,user_id) VALUES
                                                                                                                                                              (1,'활어','국산','호주','2024-07-27',10000,'방금잡은','자연산',20.0,1,1,2),
                                                                                                                                                              (2,'선어','수입','호주','2024-07-28',20000,'방금잡은','양식',10.0,1,2,2),
                                                                                                                                                              (3,'냉동','국산','호주','2024-07-29',30000,'방금잡은','자연산',50.0,1,3,2),
                                                                                                                                                              (4,'냉동','수입','호주','2024-07-28',10000,'방금잡은','양식',25.0,1,4,2),
                                                                                                                                                              (5,'선어','수입','호주','2024-07-30',10000,'방금잡은','자연산',15.0,1,5,2),
                                                                                                                                                              (6,'활어','국산','호주','2024-07-28',20000,'방금잡은','자연산',45.0,1,1,2),
                                                                                                                                                              (7,'활어','국산','호주','2024-07-27',10000,'방금잡은','양식',20.0,1,1,2),
                                                                                                                                                              (8,'냉동','국산','호주','2024-07-27',30000,'방금잡은','자연산',60.0,1,2,2),
                                                                                                                                                              (9,'선어','수입','호주','2024-07-27',50000,'방금잡은','자연산',20.0,1,2,2),
                                                                                                                                                              (10,'선어','국산','호주','2024-07-27',40000,'방금잡은','양식',24.0,1,5,2);

INSERT INTO outgoing (outgoing_id, company_id, outgoing_date, outgoing_state, partial_outgoing, user_id) VALUES
                                                                                                             (1, 1, '2024-07-27', 'PENDING', true,1),
                                                                                                             (2, 1, '2024-07-28', 'PENDING', false,2),
                                                                                                             (3, 1, '2024-07-29', 'PENDING', true,2),
                                                                                                             (4, 1, '2024-07-30', 'PENDING', false,2),
                                                                                                             (5, 1, '2024-07-31', 'PENDING', true,2),
                                                                                                             (6, 1, '2024-08-01', 'PENDING', false,2),
                                                                                                             (7, 1, '2024-08-02', 'PENDING', true,2),
                                                                                                             (8, 1, '2024-08-03', 'PENDING', false,2),
                                                                                                             (9, 1, '2024-08-04', 'PENDING', true,2),
                                                                                                             (10, 1, '2024-08-05', 'PENDING', false,2),
                                                                                                             (11, 1, '2024-08-06', 'PENDING', true,2),
                                                                                                             (12, 1, '2024-08-07', 'PENDING', false,2),
                                                                                                             (13, 1, '2024-08-08', 'PENDING', true,2),
                                                                                                             (14, 1, '2024-08-09', 'PENDING', false,2),
                                                                                                             (15, 1, '2024-08-10', 'PENDING', true,2),
                                                                                                             (16, 1, '2024-08-11', 'PENDING', false,2),
                                                                                                             (17, 1, '2024-08-12', 'PENDING', true,2),
                                                                                                             (18, 1, '2024-08-13', 'PENDING', false,2),
                                                                                                             (19, 1, '2024-08-14', 'PENDING', true,2),
                                                                                                             (20, 1, '2024-08-15', 'PENDING', false,2),
                                                                                                             (21, 1, '2024-08-16', 'PENDING', true,2),
                                                                                                             (22, 1, '2024-08-17', 'PENDING', false,2),
                                                                                                             (23, 1, '2024-08-18', 'PENDING', true,2),
                                                                                                             (24, 1, '2024-08-19', 'READY', false,2),
                                                                                                             (25, 1, '2024-08-20', 'COMPLETE', true,2),
                                                                                                             (26, 1, '2024-08-21', 'PENDING', false,2),
                                                                                                             (27, 1, '2024-08-22', 'PENDING', true,2),
                                                                                                             (28, 1, '2024-08-23', 'PENDING', false,2),
                                                                                                             (29, 1, '2024-08-24', 'PENDING', true,2),
                                                                                                             (30, 1, '2024-08-25', 'PENDING', false,2),
                                                                                                             (31, 1, '2024-08-26', 'PENDING', true,2),
                                                                                                             (32, 1, '2024-08-27', 'PENDING', false,2),
                                                                                                             (33, 1, '2024-08-28', 'PENDING', true,2),
                                                                                                             (34, 1, '2024-08-29', 'PENDING', false,2),
                                                                                                             (35, 1, '2024-08-30', 'PENDING', true,2),
                                                                                                             (36, 1, '2024-08-31', 'PENDING', false,2),
                                                                                                             (37, 1, '2024-09-01', 'PENDING', true,2),
                                                                                                             (38, 1, '2024-09-02', 'PENDING', false,2),
                                                                                                             (39, 1, '2024-09-03', 'PENDING', true,2),
                                                                                                             (40, 1, '2024-09-04', 'PENDING', false,2),
                                                                                                             (41, 1, '2024-09-05', 'PENDING', true,2),
                                                                                                             (42, 1, '2024-09-06', 'PENDING', false,2),
                                                                                                             (43, 1, '2024-09-07', 'PENDING', true,2),
                                                                                                             (44, 1, '2024-09-08', 'PENDING', false,2),
                                                                                                             (45, 1, '2024-09-09', 'PENDING', true,2),
                                                                                                             (46, 1, '2024-09-10', 'PENDING', false,2),
                                                                                                             (47, 1, '2024-09-11', 'PENDING', true,2),
                                                                                                             (48, 1, '2024-09-12', 'PENDING', false,2),
                                                                                                             (49, 1, '2024-09-13', 'PENDING', true,2),
                                                                                                             (50, 1, '2024-09-14', 'PENDING', false,2),
                                                                                                             (51, 1, '2024-09-15', 'PENDING', true,2),
                                                                                                             (52, 1, '2024-09-16', 'PENDING', false,2),
                                                                                                             (53, 1, '2024-09-17', 'PENDING', true,2),
                                                                                                             (54, 1, '2024-09-18', 'PENDING', false,2),
                                                                                                             (55, 1, '2024-09-19', 'PENDING', true,2),
                                                                                                             (56, 1, '2024-09-20', 'PENDING', false,2),
                                                                                                             (57, 1, '2024-09-21', 'PENDING', true,2),
                                                                                                             (58, 1, '2024-09-22', 'PENDING', false,2),
                                                                                                             (59, 1, '2024-09-23', 'PENDING', true,2),
                                                                                                             (60, 1, '2024-09-24', 'PENDING', false,2),
                                                                                                             (61, 1, '2024-09-25', 'PENDING', true,2),
                                                                                                             (62, 1, '2024-09-26', 'PENDING', false,2),
                                                                                                             (63, 1, '2024-09-27', 'PENDING', true,2),
                                                                                                             (64, 1, '2024-09-28', 'PENDING', false,2),
                                                                                                             (65, 1, '2024-09-29', 'PENDING', true,2),
                                                                                                             (66, 1, '2024-09-30', 'PENDING', false,2),
                                                                                                             (67, 1, '2024-10-01', 'PENDING', true,2),
                                                                                                             (68, 1, '2024-10-02', 'PENDING', false,2),
                                                                                                             (69, 1, '2024-10-03', 'PENDING', true,2),
                                                                                                             (70, 1, '2024-10-04', 'PENDING', false,2);

INSERT INTO outgoing_detail (detail_id, price, product_id, outgoing_id, quantity,category,country,natural_status) values (1,10000,1,1,30,'활어','국산','양식'),
                                                                                                                         (2,10000,5,1,40,'활어','국산','양식'),
                                                                                                                         (3,10000,3,1,50,'활어','국산','양식'),
                                                                                                                         (4,10000,4,1,60,'선어','국산','양식'),
                                                                                                                         (5,10000,6,2,70,'활어','국산','양식'),
                                                                                                                         (6,10000,5,2,80,'활어','국산','양식'),
                                                                                                                         (7,10000,3,2,90,'선어','국산','양식'),
                                                                                                                         (8,10000,1,2,100,'선어','국산','양식');
INSERT INTO inventories (inventory_id, company_id, quantity, category, product_id, country, incoming_date, natural_status) VALUES
                                                                                                                               (1, 1, 60, '활어', 1, '국산', '2024-07-23', '자연'),
                                                                                                                               (2, 1, 60, '활어', 2, '국산', '2024-07-03', '양식'),
                                                                                                                               (3, 1, 60, '선어', 1, '국산', '2024-07-17', '자연'),
                                                                                                                               (4, 1, 30, '활어', 2, '일본', '2024-07-12', '자연'),
                                                                                                                               (5, 1, 16, '선어', 1, '국산', '2024-07-31', '자연'),
                                                                                                                               (6, 1, 10, '선어', 1, '국산', '2024-07-22', '자연'),
                                                                                                                               (7, 1, 17, '활어', 2, '국산', '2024-07-28', '양식'),
                                                                                                                               (8, 1, 60, '활어', 1, '국산', '2024-07-03', '자연'),
                                                                                                                               (9, 1, 60, '활어', 1, '국산', '2024-07-17', '자연'),
                                                                                                                               (10, 1, 60, '활어', 3, '국산', '2024-07-23', '양식'),
                                                                                                                               (11, 1, 60, '활어', 1, '국산', '2024-07-23', '자연'),
                                                                                                                               (12, 1, 60, '활어', 1, '국산', '2024-07-31', '자연'),
                                                                                                                               (13, 1, 10, '활어', 4, '국산', '2024-07-28', '자연'),
                                                                                                                               (14, 1, 60, '활어', 1, '국산', '2024-07-22', '자연'),
                                                                                                                               (15, 1, 60, '활어', 2, '국산', '2024-07-12', '자연'),
                                                                                                                               (16, 1, 60, '활어', 1, '국산', '2024-07-31', '자연'),
                                                                                                                               (17, 1, 60, '활어', 1, '국산', '2024-07-17', '자연'),
                                                                                                                               (18, 1, 60, '활어', 5, '일본', '2024-07-23', '자연'),
                                                                                                                               (19, 1, 60, '활어', 1, '국산', '2024-07-31', '자연'),
                                                                                                                               (20, 1, 40, '활어', 6, '국산', '2024-07-03', '자연'),
                                                                                                                               (21, 1, 23, '활어', 5, '국산', '2024-07-03', '양식'),
                                                                                                                               (22, 1, 60, '활어', 1, '국산', '2024-07-23', '자연');

insert into adjust (ADJUST_ID,ADJUST_DATE,AFTER_QUANTITY,BEFORE_QUANTITY,REASON,INVENTORY_ID)
values (1,'2024-07-27',500,100,'어디서 주워옴',22);
INSERT INTO inventory_detail(inventory_detail_id,inventory_id,company_id,warning_quantity, outgoing_price) VALUES
                                                                                                               (1, 1, 1, 5, 10000),
                                                                                                               (2, 2, 1, 5, 10000),
                                                                                                               (3, 3, 1, 5, 10000),
                                                                                                               (4, 4, 1, 5, 10000),
                                                                                                               (5, 5, 1, 5, 10000),
                                                                                                               (6, 6, 1, 5, 10000),
                                                                                                               (7, 7, 1, 5, 10000),
                                                                                                               (8, 8, 1, 5, 10000),
                                                                                                               (9, 9, 1, 5, 10000),
                                                                                                               (10, 10, 1, 5, 10000),
                                                                                                               (11, 11, 1, 5, 10000),
                                                                                                               (12, 12, 1, 5, 10000),
                                                                                                               (13, 13, 1, 5, 10000),
                                                                                                               (14, 14, 1, 5, 10000),
                                                                                                               (15, 15, 1, 5, 10000),
                                                                                                               (16, 16, 1, 5, 10000),
                                                                                                               (17, 17, 1, 5, 10000),
                                                                                                               (18, 18, 1, 5, 10000),
                                                                                                               (19, 19, 1, 5, 10000),
                                                                                                               (20, 20, 1, 5, 10000),
                                                                                                               (21, 21, 1, 5, 10000);