# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)

App.create(app_title: 'ios', description:'From IOS app');
App.create(app_title: 'android', description:'From Android app');
App.create(app_title: 'web', description:'From default web app');
App.create(app_title: 'unknown', description:'From an unknown app');