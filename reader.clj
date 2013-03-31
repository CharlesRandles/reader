; -*-Lisp-*-

;;;;
;;;;Parse an OPML file for atom/rss feeds
;;;;
(ns reader
    (:require opml))
 
(def subscriptions "http://localhost/reader/subscriptions.xml") ;Because that's where I put it.

(defn retrieve-feed [feed]
  "Return the raw feed retrieved from the XML URL of the feed"
