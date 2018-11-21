(ns test-api.queries.test
  (require [yesql.core :refer [defqueries]]
           [clojure.data.json :as json]
           [cheshire.core :refer :all]))

(def db-spec {:classname   "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname     "//localhost:5432/test"
              :user        "test"
              :password    "test"})

(defqueries "test_api/queries/test.sql"
            {:connection db-spec})


;; JSON-parsers

(defn json2edn [data]
  (parse-string (json/read-str (json/write-str data)) true))

(defn edn2json [data]
  (json/write-str (into {} data)))


;; QUERIES

(defn all-tests []
  (all-test-messages))

(defn one-test [id]
  (edn2json (test-by-id {:id id})))

(defn new-test-body [body]
  (edn2json (new-test<! (json2edn body))))

(defn update-test [])