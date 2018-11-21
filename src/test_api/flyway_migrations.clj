(ns test-api.flyway-migrations
  (:import org.flywaydb.core.Flyway
           org.flywaydb.core.internal.info.MigrationInfoDumper))

;; Build DB String
(def db-url (str "jdbc:postgresql://localhost:5432/test"))

;; Initialize Flyway object
(def flyway
  (let [locations (into-array String ["classpath:db/migrations"])]
    (doto (new Flyway)
      (.setDataSource db-url (str "test") (str "test") (into-array String []))
      (.setLocations locations))))

(defn migrate [] (.migrate flyway))

(defn clean [] (.clean flyway))

(defn reset [] (clean) (migrate))

(defn info []
  (println (MigrationInfoDumper/dumpToAsciiTable (.all (.info flyway)))))