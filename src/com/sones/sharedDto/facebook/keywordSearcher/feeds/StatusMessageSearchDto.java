package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import com.sones.facebook.model.feed.StatusMessage;

/**
 * Search dto for {@link StatusMessage} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class StatusMessageSearchDto extends FacebookPostSearchDto	implements	ISearchableFacebookFeed
{
	/**
	 * The status message message.
	 */
	private	String	message;
	
	/**
	 * Initializes the object.
	 */
	public StatusMessageSearchDto()
	{
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Returns true if the status message contains the value. It's not case sensitive.
	 * @param value
	 * @return true if post contains the value.
	 */
	@Override
	public	boolean	contains(String value)
	{
		if( message != null )
		{
			if(message.toLowerCase().contains(value.toLowerCase()))
			{
				return	true;
			}
		}
		return	false;
	}
}
