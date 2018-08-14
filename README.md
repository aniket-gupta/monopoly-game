# Bankerless Monopoly:

Monopoly is a classic board game where players roll two six-sided dice to move around the game board, buying and trading properties.

## How to run code
```sh
git clone https://github.com/aniket-gupta/monopoly-game.git
cd monopoly-game
mvn clean install assembly:single
java -jar target/bankerless-monopolygame-1.0-SNAPSHOT-jar-with-dependencies.jar test-data/sample-input.json
```

example input file
```json
{
  "init": {
    "players": [
      "Player A",
      "Player B",
      "Player C"
    ],
    "properties": [
      {
        "name": "Cochin",
        "cost": 120,
        "color": "Green",
        "rentPerLevel": [
          100,
          160,
          260,
          440,
          860
        ]
      },
      {
        "name": "Ooty",
        "cost": 400,
        "color": "Green",
        "rentPerLevel": [
          300,
          400,
          560,
          810,
          1600
        ]
      },
      {
        "name": "Bombay",
        "cost": 500,
        "color": "Red",
        "rentPerLevel": [
          400,
          520,
          680,
          900,
          1800
        ]
      },
      {
        "name": "Ahmedabad",
        "cost": 300,
        "color": "Red",
        "rentPerLevel": [
          200,
          350,
          480,
          800,
          1200
        ]
      },
      {
        "name": "Chennai",
        "cost": 700,
        "color": "Blue",
        "rentPerLevel": [
          600,
          900,
          1250,
          1500,
          1900
        ]
      },
      {
        "name": "Bangalore",
        "cost": 450,
        "color": "Blue",
        "rentPerLevel": [
          300,
          400,
          560,
          810,
          1600
        ]
      },
      {
        "name": "Delhi",
        "cost": 500,
        "color": "Yellow",
        "rentPerLevel": [
          400,
          520,
          680,
          900,
          1800
        ]
      },
      {
        "name": "Darjeeling",
        "cost": 600,
        "color": "Yellow",
        "rentPerLevel": [
          400,
          700,
          1000,
          1150,
          1400
        ]
      }
    ]
  },
  "input": [
    "Player A lands on Bombay",
    "Player B lands on Delhi",
    "Player C lands on Chennai",
    "Player A lands on Special",
    "Player B lands on Bombay",
    "Player C lands on Ooty",
    "Player A lands on Ahmedabad",
    "Player B lands on Darjeeling",
    "Player C lands on Special",
    "Player A lands on Delhi",
    "Player B crosses GO",
    "Player C lands on Bombay"
  ]
}
```


## User story

1. Each player is seeded with $2,000 at the beginning of the game by the banker.
2. As the player crosses GO, he is seeded with $200.
3. players moves around the board and land on properties, they can buy unowned properties, build houses, pay rent if the property belongs to another player, or mortgage properties to pay rent.
4. If a player owes more money than his/her assets can afford, he/she is declared bankrupt and is out of the game.

5. board has property tiles
6. board has one special tile
7. board has one GO tile

8. Property has the cost value
9. Property has color
10. Property has its rent levels (1-5).
11. Rent levels, start at 1, go up by 1 right after a successful rent payment for a property.
12. Special tile - increases the rent level by 1 for one of the highest value property for the player, decreases the rent level by 1 for the highest value property held by the opponents.
13. Rent level cannot go lower than 1.






