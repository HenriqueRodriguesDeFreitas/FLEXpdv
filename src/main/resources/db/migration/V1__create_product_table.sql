CREATE TABLE IF NOT EXISTS product(
    id UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(150) NOT NULL,
    barcode VARCHAR(20) NOT NULL UNIQUE,
    cost_price NUMERIC(10,2) NOT NULL,
    sale_price NUMERIC(10,2) NOT NULL,
    stock NUMERIC(10,2) DEFAULT 0,
    active boolean DEFAULT true,
    stock_control boolean DEFAULT true
)