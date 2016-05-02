(defproject numeralis "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.1"]
                 [compojure "1.5.0"]
                 [duct "0.5.10"]
                 [environ "1.0.2"]
                 [meta-merge "0.1.1"]
                 [ring "1.4.0"]
                 [ring-middleware-format "0.7.0"]
                 [ring/ring-defaults "0.2.0"]
                 [ring/ring-mock "0.3.0"]
                 [ring-jetty-component "0.3.1"]
                 [org.slf4j/slf4j-nop "1.7.14"]]
  :plugins [[lein-environ "1.0.2"]
            [lein-gen "0.2.2"]]
  :generators [[duct/generators "0.5.10"]]
  :duct {:ns-prefix numeralis}
  :main ^:skip-aot numeralis.main
  :uberjar-name "numeralis-standalone.jar"
  :target-path "target/%s/"
  :aliases {"gen"    ["generate"]
            "setup"  ["do" ["generate" "locals"]]
            "deploy" ["do"
                      ["vcs" "assert-committed"]
                      ["vcs" "push" "heroku" "master"]]}
  :profiles
  {:dev           [:project/dev  :profiles/dev]
   :test          [:project/test :profiles/test]
   :uberjar       {:aot :all}
   :profiles/dev  {}
   :profiles/test {}
   :project/dev   {:dependencies [[reloaded.repl "0.2.1"]
                                  [org.clojure/tools.namespace "0.2.11"]
                                  [org.clojure/tools.nrepl "0.2.12"]
                                  [eftest "0.1.1"]]
                   :source-paths ["dev"]
                   :repl-options {:init-ns user}
                   :env          {:port "3000"}}
   :project/test  {}})
