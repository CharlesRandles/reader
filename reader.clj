; -*-Lisp-*-

;;;;
;;;;Parse an OPML file for atom/rss feeds
;;;;
(ns reader
    (:require opml)
    (:require clojure.xml))
 
(def subscriptions "http://localhost/reader/subscriptions.xml") ;Because that's where I put it.

(defn retrieve-feed [feed]
  "Grab and parse the XML from an RSS or Atom feed"
  (try
   (clojure.xml/parse (feed :xmlUrl))
   (catch Exception ex (println "Failed to retrieve " (feed :title) ": " (str ex)))))

(defn reader-test []
 (println (map retrieve-feed (take 3 (opml/subscriptions subscriptions)))))

(reader-test)