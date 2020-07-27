alter table PROMOTION drop constraint promotion_product_fk;
alter table PROMOTION drop constraint promotion_category_fk;
alter table PROMOTION drop constraint promotion_delivery_fk;
alter table PROMOTION drop constraint one_promotion;
drop index product_id_idx;
alter table PROMOTION drop column product_id;
alter table PROMOTION drop column category_id;
alter table PROMOTION drop column delivery_id;

alter table PRODUCT add promotion_id NUMBER(4);
alter table PRODUCT add constraint product_promotion_fk FOREIGN KEY (promotion_id) references PROMOTION(promotion_id);
create index product_promotion_idx on PRODUCT(promotion_id);

alter table CATEGORY add promotion_id NUMBER(4);
alter table CATEGORY add constraint category_promotion_fk FOREIGN KEY (promotion_id) references PROMOTION(promotion_id);
create index category_promotion_idx on CATEGORY(promotion_id);

alter table DELIVERY add promotion_id NUMBER(4);
alter table DELIVERY add constraint delivery_promotion_fk FOREIGN KEY (promotion_id) references PROMOTION(promotion_id);
create index delivery_promotion_fk on DELIVERY(promotion_id);