/**
 * 	This file is part of Kayak.
 *
 *	Kayak is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU Lesser General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	Kayak is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU Lesser General Public License
 *	along with Kayak.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.github.kayak.ui.messageview;

import com.github.kayak.core.description.BusDescription;
import com.github.kayak.core.description.MessageDescription;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author Jan-Niklas Meier <dschanoeh@googlemail.com>
 */
public class MessageNodeFactory extends ChildFactory<MessageDescription> {

    private BusDescription description;
    
    public MessageNodeFactory(BusDescription description) {
	this.description = description;	
    }
    
    @Override
    protected boolean createKeys(List<MessageDescription> list) {
	for(Integer key : description.getMessages().keySet()) {
	    list.add(description.getMessages().get(key));
	}

	return true;
    }

    @Override
    protected Node[] createNodesForKey(MessageDescription key) {
	return new Node[] { new MessageDescriptionNode(key) };	     
    }
    
}
