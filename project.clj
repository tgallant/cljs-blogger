(defproject cljs-blogger "0.1.0-SNAPSHOT"
  :description "A simple clojurescript/markdown blog platform"
  :url "https://github.com/tgallant/cljs-blogger"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2322"]
                 [org.clojure/core.async "0.1.338.0-5c5012-alpha"]
                 [markdown-clj "0.9.47"]
                 [cljs-http "0.1.16"]
                 [secretary "1.2.0"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]]

  :source-paths ["src"]

  :cljsbuild {
    :builds [{:id "blog"
              :source-paths ["src"]
              :compiler {
                :output-to "js/main.js"
                :optimizations :advanced
                :pretty-print true}}]})
