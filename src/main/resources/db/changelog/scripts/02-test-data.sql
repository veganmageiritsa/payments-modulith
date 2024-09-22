
INSERT INTO inventory (id, description, name, price) VALUES ( nextval('inventory_seq'),'for writing', 'pencil', 500.00);
INSERT INTO inventory (id, description, name, price) VALUES ( nextval('inventory_seq'),'for ruling', 'ruler', 100.00);
INSERT INTO inventory (id, description, name, price) VALUES ( nextval('inventory_seq'),'for taking notes', 'book', 600.00);
INSERT INTO inventory (id, description, name, price) VALUES ( nextval('inventory_seq'),'for drawing', 'drawing_pad', 700.00);
INSERT INTO inventory (id, description, name, price) VALUES ( nextval('inventory_seq'),'for packing lunch', 'lunchbox', 800.00);



-- alter table cards add constraint fk_cards_app_users foreign key (app_user_id) references app_users (id);

-- CREATE INDEX idx_user ON cards (app_user_id);


