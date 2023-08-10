(defproject clojure-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0",
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [compojure "1.6.1"]
                 [http-kit "2.3.0"]
                 [ring "1.7.1"]
                 [ring/ring-json "0.5.0"]
                 [ring/ring-defaults "0.3.2"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.postgresql/postgresql "42.2.16.jre7"]
                 [migratus "1.5.1"]]
  :main ^:skip-aot clojure-api.core
  :plugins [[migratus-lein "0.7.3"]]
  :migratus {:store :database,
             :migration-dir "migrations",
             :db {:dbtype "postgresql",
                  :dbname "postgres",
                  :user "postgres",
                  :password "ajay"}}
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all,
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
