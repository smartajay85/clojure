(ns clojure-api.db
    (:require [clojure.java.jdbc :as sql])
    (:gen-class))

(def db-config {:dbtype "postgresql"
            :dbname "postgres"
            :host "localhost"
            :user "postgres"
            :password "ajay"})