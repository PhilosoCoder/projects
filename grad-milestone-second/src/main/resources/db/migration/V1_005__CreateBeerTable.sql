CREATE TABLE beer (
    beer_id UUID PRIMARY KEY NOT NULL,
    version INTEGER,
    beer_name TEXT NOT NULL,
    beer_style TEXT NOT NULL,
    upc TEXT NOT NULL,
    quantity_on_hand INTEGER,
    price DECIMAL NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
)