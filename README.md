# JavaEnContinu

Projet fil rouge de la formation DTA Ingénierie, menée par Diginamic.
Session d09 - 26 Juin au 15 Septembre 2017

## Build Status

[![Build Status](http://jenkins.cleverapps.io/buildStatus/icon?job=pierre-antoine-pizzeria)](http://jenkins.cleverapps.io/job/pierre-antoine-pizzeria/)

## Présentation

Le projet représente une application de gestion de pizzeria. L'application est décomposée en 3 parties :
* L'IHM (Interface Homme-Machine)
* La DAO (Data Access Object)
* Le modèle

L'IHM se compose d'un menu, affichant chaque action dudit menu (lister les pizzas, ajouter une pizza, etc.)

La DAO gère le stockage des pizzas en mémoire ou en base de données, selon les paramètres d'entrés

Le modèle est représenté ici par une Pizza, avec ces attributs : id, code, nom, prix, catégorie
