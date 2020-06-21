insert into category (category_id, category_name) values (category_id_sequence.nextval, 'Electronics');
insert into category (category_id, category_name, parent_category_id) select category_id_sequence.nextval, 'Smartphones', 
    category_id from category where category_name in ('Electronics');
    
insert into product values (product_id_sequence.nextval, 'Xiaomi Redmi 8A 2 + 32 GB LTE Dual SIM Ocean Blue', 585.00, 99, 
    'Xiaomi Redmi 8A 32 GB to smartfon na miarę XXI wieku. Jest to urządzenie korzystające w nowoczesnych 
    technologii, które możesz kupić w bardzo dobrej cenie! Sprzęt został wyposażony w powłokę odporną na 
    zachlapania, a także niezwykle nowoczesny font. Sam wyświetlacz został wykonany w technologii IPS, 
    cechuje się rozdzielczością na poziomie HD+, a przekątna ekranu wynosi 6,22”. Urządzenie zostało wykonane 
    z najlepszych materiałów, zatem jest odporne i będzie Ci służyło przez długi czas.', 0, 0.19, 'Xiaomi', 2020, 
    '75.41 mm/156.48 mm/9.4 mm', 2);
    
