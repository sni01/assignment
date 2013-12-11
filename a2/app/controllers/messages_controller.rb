class MessagesController < ApplicationController
  skip_before_action :verify_authenticity_token
  def index
     @messages = Message.all.order('created_at DESC')
	 @app = App.all
  end
  def new
     @message = Message.new
  end
  def create
     @message = Message.new(message_params)
     @message.save
  end

  def show
     @message = Message.find_by_id(params[:id])
  end
  
  private
    def message_params
      params.require(:message).permit(:username, :content, :app_id)
    end
end
