DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_proc
        WHERE proname = 'remove_accent'
    ) THEN
        EXECUTE '
        CREATE OR REPLACE FUNCTION remove_accent(text)
        RETURNS text AS $func$
        BEGIN
            RETURN translate($1,
            ''áàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđ'',
            ''aaaaaaaaaaaaaaaaaeeeeeeeeeeeiiiiiooooooooooooooooouuuuuuuuuuuyyyyyd'');
        END;
        $func$ LANGUAGE plpgsql;';
    END IF;
END $$;
