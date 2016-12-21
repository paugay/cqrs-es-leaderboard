# cqrs-es-leaderboard


resources:
- https://github.com/ruprict/ccleader
- https://github.com/CodelyTV/cqrs-ddd-example
- https://github.com/Hyunk3l/event-sourcing-todolist
- https://github.com/broadway/broadway
- https://github.com/erikrozendaal/cqrs-lottery


## Postgres
https://www.moncefbelyamani.com/how-to-install-postgresql-on-a-mac-with-homebrew-and-lunchy/

createdb eventstore
psql eventstore -c "CREATE USER eventstore_admin PASSWORD 'admin';"
psql eventstore -c "GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO eventstore_admin;"

psql -d eventstore -U eventstore_admin

DROP TABLE eventstore;
CREATE TABLE IF NOT EXISTS eventstore (
    id              SERIAL PRIMARY KEY,
    event_id        VARCHAR(36) NOT NULL,
    event_type      VARCHAR(255) NOT NULL,
    aggregate_id    VARCHAR(36) NOT NULL,
    aggregate_type  VARCHAR(255) NOT NULL,
    data            TEXT NOT NULL,
    occured_on      TIMESTAMP NOT NULL
);

DROP TABLE player;
CREATE TABLE IF NOT EXISTS player (
    id              VARCHAR(36) NOT NULL PRIMARY KEY,
    username        VARCHAR(255) NOT NULL,
    name            VARCHAR(255),
    email           VARCHAR(255)
);
    

