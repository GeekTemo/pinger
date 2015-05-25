(defproject pinger "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [org.clojure/tools.logging "0.3.1"]
                           [log4j/log4j "1.2.17"]
                           [javax.mail/mail "1.4.7"]]
            :main ^:skip-aot pinger.core
            :target-path "target/%s"
            :profiles {:uberjar {:aot :all}})
