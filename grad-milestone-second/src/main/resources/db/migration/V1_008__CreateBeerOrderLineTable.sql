CREATE TABLE beer_order_line (
    id UUID PRIMARY KEY NOT NULL,
    beer_id UUID DEFAULT NULL,
    created_at TIMESTAMP DEFAULT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    order_quantity INTEGER DEFAULT NULL,
    quantity_allocated INTEGER DEFAULT NULL,
    version BIGINT DEFAULT NULL,
    beer_order_id UUID DEFAULT NULL,

    CONSTRAINT fk_beer_order_line_beer_order_id FOREIGN KEY (beer_order_id) REFERENCES beer_order (beer_order_id),
    CONSTRAINT fk_beer_order_line_beer_id FOREIGN KEY (beer_id) REFERENCES beer (beer_id)
)