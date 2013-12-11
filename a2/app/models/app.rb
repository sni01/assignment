class App < ActiveRecord::Base
  has_many :message
  validates :app_title, :description, :app_id, :presence => true
end
