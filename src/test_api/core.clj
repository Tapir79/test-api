(ns test-api.core
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.json-response :as json-response]
            [ring.middleware.cors :refer [wrap-cors]]
            [test-api.flyway-migrations :as flyway]
            [test-api.queries.test :as sql-test]))

(defn get-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (:body (json-response/json-response (sql-test/all-tests)))})

(defn get-one-handler [id]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (:body (json-response/json-response (sql-test/one-test id)))})

(defn post-new-handler [body]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (sql-test/new-test-body body)})

(defn general-handler [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "A general handler for anything!"})

(defroutes app-routes
           (GET "/tests" [] get-handler)
           (GET "/tests/:id" [id] (get-one-handler (read-string id)))
           (POST "/tests" {body :body} (post-new-handler (slurp body)))
           (ANY "/anything-goes" [] general-handler)
           (route/not-found "The route was not found!"))

(def app
  (-> app-routes
      (wrap-cors
        :access-control-allow-origin [#".*"]
        :access-control-allow-headers ["Origin" "X-Requested-With" "Content-Type" "Accept"]
        :access-control-allow-methods [:get :post :put])))

(defn -main
  "This is our app's entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
    (server/run-server #'app {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))
    (flyway/reset)))