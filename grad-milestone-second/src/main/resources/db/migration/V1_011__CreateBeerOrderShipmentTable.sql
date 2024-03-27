CREATE TABLE beer_order_shipment (
    beer_order_shipment_id UUID NOT NULL PRIMARY KEY,
    beer_order_id UUID UNIQUE,
    tracking_number TEXT,
    created_at TIMESTAMP DEFAULT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    version BIGINT DEFAULT NULL,

    CONSTRAINT fk_beer_order_shipment_beer_order FOREIGN KEY (beer_order_id) REFERENCES beer_order (beer_order_id)
);

ALTER TABLE beer_order
    ADD CONSTRAINT fk_beer_order_beer_order_shipment_id
    FOREIGN KEY (beer_order_shipment_id) REFERENCES beer_order_shipment (beer_order_shipment_id);
