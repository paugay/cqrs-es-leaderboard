# CQRS-ES Leaderboard

This is an implementation of a leaderboard, which basically consists on a ranking of players sorted by their score, 
and you create new players and add game results. 


## API documentation

#### `POST /leaderboard/create`

Creates a new player into the leaderboard.

##### Body

- `username` (Key) The username for the user  
- `name`  The name of the user
- `email` The email of the user

##### Success response

`202 ACCEPTED` with no data


##### Sample call
 
```
curl -X POST -d '{ "username": "pau", "name": "Pau Gay", "email": "pau@schibsted.com" }' -H "Content-Type: application/json" http://$API/leaderboard/create
```


#### `POST /leaderboard/add`

Adds a new game result into the leaderboard.

##### Body

- `home_player_id` The username of the player who played at home  
- `home_score`  The score of the player who played at home
- `away_player_id` The username of the player who played away
- `away_score` The score of the player who played away

##### Success response

`202 ACCEPTED` with no data


##### Sample call
 
```
curl -X POST -d '{ "home_player_id": "miguel", "home_score": 5, "away_player_id": "pau", "away_score": 3 }' -H "Content-Type: application/json" http://$API/leaderboard/game
```

#### `GET /leaderboard/ranking`

Returns the current ranking. 

#### Success response

`200 OK` containig the JSON representation of the ranking

#### Sample call
```
curl -H "Content-Type: application/json" http://$API/leaderboard/ranking

{
  "1100": {
    "username": "miguel",
    "name": "Miguel Olmos",
    "email": "miguel@schibsted.com"
  },
  "900": {
    "username": "pau",
    "name": "Pau Gay",
    "email": "pau@schibsted.com"
  }
}
```

## Installation

### 1. PostgreSQL

Install Postgres according with the following tutorial:
https://www.moncefbelyamani.com/how-to-install-postgresql-on-a-mac-with-homebrew-and-lunchy/

Create table, user, and grant permission:
```
createdb eventstore
psql eventstore -c "CREATE USER eventstore_admin PASSWORD 'admin';"
psql eventstore -c "GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO eventstore_admin;"
```

Enter into the psql interactive terminal:
 
`[> psql -d eventstore -U eventstore_admin`

And create the schema as follows:

```
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
```
    
### 2. Redis

Follow:
https://redis.io/download#installation

### 3. Code

Clone the project, and run it.
```
git clone <project>
cd <project>

./gradlew bootRun
```

## Usage sample

Set API hostname, create a couple of users and fetch the ranking.
```
export API=localhost:8080
curl -X POST -d '{ "username": "miguel", "name": "Miguel Olmos", "email": "miguel@schibsted.com" }' -H "Content-Type: application/json" http://$API/leaderboard/create
curl -X POST -d '{ "username": "pau", "name": "Pau Gay", "email": "pau@schibsted.com" }' -H "Content-Type: application/json" http://$API/leaderboard/create
curl -H "Content-Type: application/json" http://$API/leaderboard/ranking | jq
```

Now, create a game and fetch the ranking again.
```
curl -X POST -d '{ "home_player_id": "miguel", "home_score": 5, "away_player_id": "pau", "away_score": 3 }' -H "Content-Type: application/json" http://$API/leaderboard/game
curl -H "Content-Type: application/json" http://$API/leaderboard/ranking | jq
```

And finally, create another game and fetch the ranking again.
```
curl -X POST -d '{ "home_player_id": "miguel", "home_score": 1, "away_player_id": "pau", "away_score": 2 }' -H "Content-Type: application/json" http://$API/leaderboard/game
curl -H "Content-Type: application/json" http://$API/leaderboard/ranking | jq
```

## Resources

The following resources has been used as sample projects of CQRS, ES or both.

- https://github.com/CodelyTV/cqrs-ddd-example -- It is a sample CQRS implementation of a video thing 
(it's uncompleted, I'm unsure what is it for) from our friends [@CodelyTv](https://twitter.com/codelytv)
- https://github.com/Hyunk3l/event-sourcing-todolist -- Some experiments that my friends [Fabri](https://twitter.com/Hyunk3l) and [Alberto](https://twitter.com/aramirez_) are 
playing with, a Todo List build with Event Sourcing  
- https://github.com/broadway/broadway -- CQRS-ES framework built by Qandidate (a company in the recruiting market) 
in PHP, it contains some cool examples 
- https://github.com/erikrozendaal/cqrs-lottery -- Very old implementation of a lottery system built with CQRS, pure 
Java with some strong focus on performance, it's one of the few CQRS implementations I've seen with versioning
