require 'couchDB'
begin
server = CouchDB::Server.new "67.23.79.113",5984
rescue
puts "sorry,failed to connect server"
end
db = CouchDB::Database.new server,"shani"
db.delete_if_exists!
db.create_if_missing!
document = CouchDB::Document.new db,"_id" => "firstdocument", "Name" => "Sha Ni"
document.save
