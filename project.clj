(defproject test-api "0.1.0"
  :description "test-api project"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.3.0"]
                 [compojure "1.6.1"]
                 [ring-cors "0.1.12"]
                 [ring-json-response "0.2.0"]
                 [org.clojure/data.json "0.2.6"]
                 [cheshire "5.8.1"]

                 ;; Database
                 [yesql "0.5.3"]
                 [org.postgresql/postgresql "42.2.2"]
                 [org.flywaydb/flyway-core "5.0.7"]]
  :plugins [[lein-environ "1.1.0"]]
  :main ^:skip-aot test-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
