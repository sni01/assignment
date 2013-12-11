class Message < ActiveRecord::Base
   validates :app_id, :presence => true
   validates :content, :length => { :maximum => 160 }, :presence => true
   validates :username, :length => { :in => 3..60}, :format => { :with => /\A[a-zA-Z0-9-_]+\Z/ }, :presence => true
end
