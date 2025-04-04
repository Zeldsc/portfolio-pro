-- Criação do usuário postgres com senha 'admin' (se necessário)
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_roles WHERE rolname = 'postgres') THEN
        CREATE ROLE postgres WITH LOGIN PASSWORD 'admin';
    END IF;
END$$;

-- Garantir permissões no banco atual (caso necessário)
GRANT CONNECT ON DATABASE portfoliopro TO postgres;
GRANT USAGE ON SCHEMA public TO postgres;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO postgres;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO postgres;
