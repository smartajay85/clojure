(ns clojure-api.core
  (:require 
   [compojure.core :refer [defroutes]]
   [compojure.route :refer [not-found]]
   [ring.middleware.defaults :refer :all]
   [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
   [clojure-api.controllers.employees :as employeeController]
   [ring.middleware.defaults :refer :all]
   [ring.adapter.jetty :refer [run-jetty]]))

(defroutes route
  employeeController/routes
  (not-found
   {:error "Not found"}))

(def app
  (-> route
      (wrap-json-body {:keywords? true :bigdecimals? true})
      wrap-json-response))

(defn -main [] (run-jetty app {:port 3000}))