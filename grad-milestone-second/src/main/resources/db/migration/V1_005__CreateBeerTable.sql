CREATE TABLE beer (
    beer_id UUID PRIMARY KEY,
    version INTEGER,
    beer_name TEXT,
    beer_style TEXT,
    upc TEXT,
    quantity_on_hand INTEGER,
    price DECIMAL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);