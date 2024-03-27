CREATE TABLE beer_category(
    beer_id UUID NOT NULL,
    category_id UUID NOT NULL,
    primary key (beer_id, category_id),
    constraint fk_beer_category_beer_id FOREIGN KEY (beer_id) references beer (beer_id),
    constraint fk_beer_category_category_id FOREIGN KEY (category_id) references category (category_id)
)