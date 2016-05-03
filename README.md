# Numeralia [![Circle CI](https://circleci.com/gh/omartell/numeralia.svg?style=svg&circle-token=e34d9add3b46a9779014df53f1fbd498586eddda)](https://circleci.com/gh/omartell/numeralia)

Translate numbers into English 🇬🇧

## Usage

Send a GET request to `/numerals/{n}` with a number smaller than one trillion. You can click on the following links to see a few examples: [172](http://numeralia.herokuapp.com/numerals/172), [1,789](http://numeralia.herokuapp.com/numerals/1789), [9,873,456](http://numeralia.herokuapp.com/numerals/9873456).

Alternatively, use a tool like CURL to change the Accept header and get different representations:

JSON
```
curl -i -H "Accept: application/json" http://numeralia.herokuapp.com/numerals/279
```
EDN
```
curl -i -H "Accept: application/edn" http://numeralia.herokuapp.com/numerals/72341
```
YAML
```
curl -i -H "Accept: application/x-yaml" http://numeralia.herokuapp.com/numerals/384347
```

## Structure
- The core of the translation logic is defined in [numeralia.core](https://github.com/omartell/numeralia/blob/master/src/numeralia/core.clj) and the main function that does the actual conversion is [`number->english`](https://github.com/omartell/numeralia/blob/master/src/numeralia/core.clj#L36).
- The endpoint `/numerals` is defined in [numeralia.endpoint.numerals](https://github.com/omartell/numeralia/blob/master/src/numeralia/endpoint/numerals.clj) to accept HTTP GET requests to translate numbers.
- The project uses [duct](http://github.com/weavejester/duct) as a template for the project to facilitate setup and deployment to heroku.
- The request accept header and response content type is handled by a ring middleware provided by [ring.middleware.format](https://github.com/ngrunwald/ring-middleware-format).
- The application gets bootstrapped using [component](http://github.com/stuartsierra/component) by defining a handler, endpoint and server component in [system.clj](https://github.com/omartell/numeralia/blob/master/src/numeralia/system.clj). The entry point is in [main.clj](https://github.com/omartell/numeralia/blob/master/src/numeralia/main.clj).

## Developing

### Setup

When you first clone this repository, run:

```sh
lein setup
```

This will create files for local configuration, and prep your system
for the project.

Next connect the repository to the [Heroku][] app:

```sh
heroku git:remote -a FIXME
```

[heroku]: https://www.heroku.com/

### Environment

To begin developing, start with a REPL.

```sh
lein repl
```

Run `go` to initiate and start the system.

```clojure
user=> (go)
:started
```

By default this creates a web server at <http://localhost:3000>.

When you make changes to your source files, use `reset` to reload any
modified files and reset the server.

```clojure
user=> (reset)
:reloading (...)
:resumed
```

### Testing

Testing is fastest through the REPL, as you avoid environment startup
time.

```clojure
user=> (test)
...
```

But you can also run tests through Leiningen.

```sh
lein test
```

## Deploying

To deploy the project, run:

```sh
lein deploy
```

## Legal

Copyright © 2016 Oliver Martell
