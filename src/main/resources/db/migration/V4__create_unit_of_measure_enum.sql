
DO $$
 BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'unit_of_measure') THEN
 CREATE TYPE unit_of_measure AS ENUM ('UN', 'L', 'KG');
 END IF;
END
$$;
