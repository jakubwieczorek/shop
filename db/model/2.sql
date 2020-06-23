insert into category (category_name) values ('Electronics');
insert into category (category_name, parent_category_name) values ('Smartphones', 'Electronics');
    
insert into product values (product_id_sequence.nextval, 'Xiaomi Redmi 8A 2 + 32 GB LTE Dual SIM Ocean Blue', 585.00, 99, 
    'Xiaomi Redmi 8A 32 GB to smartfon na miarę XXI wieku. Jest to urządzenie korzystające w nowoczesnych 
    technologii, które możesz kupić w bardzo dobrej cenie! Sprzęt został wyposażony w powłokę odporną na 
    zachlapania, a także niezwykle nowoczesny font. Sam wyświetlacz został wykonany w technologii IPS, 
    cechuje się rozdzielczością na poziomie HD+, a przekątna ekranu wynosi 6,22”. Urządzenie zostało wykonane 
    z najlepszych materiałów, zatem jest odporne i będzie Ci służyło przez długi czas.', 0, 0.19, 'Xiaomi', 2020, 
    '75.41 mm/156.48 mm/9.4 mm', 'Smartphones');

insert into product values (product_id_sequence.nextval, 'HUAWEI MATE 20 PRO 6/128GB - TWILIGHT + GRATIS', 580.00, 5, 
    'Huawei Mate 20 Pro jest po prostu piękny. To dzięki dwóm taflom szkła, które pokrywają przód i tył urządzenia. 
     Wewnątrz obudowy skrywa się bardzo wydajny procesor Kirin 980, który został wyprodukowany w procesie 7nm. 
     Dzięki wykorzystywanej funkcji AI (sztucznej inteligencji), potrójny aparat Leica pozwoli Ci na zrobienia 
     rewelacyjnych zdjęć, zarówno w dzień, jak i w nocy.', 2, 0.2, 'huawei', 2020, 
    '74.41 mm/155.48 mm/8.9 mm', 'Smartphones');
    
insert into product values (product_id_sequence.nextval, 'Smartfon Apple iPhone 7 różowe złoto 32 GB', 600.00, 300, 
    'iPhone 7 radykalnie udoskonala kluczowe elementy iPhone’a. Przynosi nowe, zaawansowane aparaty do zdjęć i wideo.
     Największą moc i najwydajniejszą baterię w historii tego urządzenia. Głośniki stereo hipnotyzujące fantastycznym 
     dźwiękiem. Najjaśniejszy i wyświetlający najszerszą gamę kolorów ekran. Odporność na wodę i zachlapania.1 Oraz 
     imponujący wygląd, na który zasługuje coś tak potężnego. Oto on. iPhone 7.', 5, 0.21, 'Apple', 2018, 
    '68.41 mm/160.48 mm/8.4 mm', 'Smartphones');
    
insert into category (category_name, parent_category_name) values ('Tablets', 'Electronics');
    
insert into product values (product_id_sequence.nextval, 'SAMSUNG GALAXY TAB S6 LTE SM-T865 DYSTR.PL GWAR', 2697.00, 62, 
    'Nowość!! Tablet Samsung Galaxy Tab S6 256GB LTE (S Pen w zestawie) Marka: Samsung Model: SM-T865NZALATO
     Kolor: szary Przekątna ekranu: 10,5 " Wbudowana pamięć: 256 GB Pamięć RAM: 8 GB Komunikacja: Bluetooth, Wi-Fi, LTE/4G
     Złącza: USB 3.1 typ C System operacyjny: Android Pojemność akumulatora: 7040 mAh Slot karty pamięci: tak, 
     MicroSD (TransFlash)', 3, 0.4, 'Samsung', 2020, '159.41 mm/244.48 mm/5.4 mm', 'Tablets');
     
insert into product values (product_id_sequence.nextval, 'TABLET HP 612 G1 i5 8/128GB / Windows / Gwarancja', 1000.00, 50, 
    'HP 612 G1 został zaprojektowany by sprostać najwyższym wymaganiom stawianym przez środowisko biznesowe. Należąc do 
     flagowej rodziny notebooków marki HP, cechuje się wysoką jakością wykonania, eleganckim designem, rozbudowanymi 
     opcjami bezpieczeństwa danych i podwyższoną funkcjonalnością, co czyni go idealnym narzędziem stabilnej pracy 
     w każdych warunkach.', 17, 0.55, 'HP', 2020, '159.41 mm/244.48 mm/5.4 mm', 'Tablets');
          
insert into Delivery_Company values ('Poczta Polska');
insert into Customer values ('malgorzata1234@op.pl', 'Kowalska', 'Małgorzata', 'Dobra', '72-003', 5, '512675777', 'Miła', null, null);

insert into Delivery values (DELIVERY_ID_SEQUENCE.nextval, 88.99, TO_DATE('25/06/2020', 'DD/MM/YYYY'), 'Poczta Polska');
insert into "Order" values (ORDER_ID_SEQUENCE.nextval, 'malgorzata1234@op.pl', 1, 0, 0, 0);

insert into Product_Order values (2, 1, 1, 'malgorzata1234@op.pl', 'Smartphones');
insert into Product_Order values (1, 3, 1, 'malgorzata1234@op.pl', 'Smartphones');

insert into Promotion values (PROMOTION_ID_SEQUENCE.nextval, null, 30.00, '30% for all tablets!', 
    TO_DATE('30/06/2020', 'DD/MM/YYYY'),  null, 'Tablets', null);

--insert into Promotion values (PROMOTION_ID_SEQUENCE.nextval, 1, 30.00, '30% for all tablets!', 
--    TO_DATE('30/06/2020', 'DD/MM/YYYY'),  null, 'Tablets', null); -- one promotion check violated ok

