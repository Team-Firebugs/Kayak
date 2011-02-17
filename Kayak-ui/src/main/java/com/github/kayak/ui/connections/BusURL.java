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

package com.github.kayak.ui.connections;

import java.io.Serializable;
import java.util.Map;

/**
 * URL class that absoulutely defines the connection parameters for a single bus
 * that is provided by a socketcand
 * @author Jan-Niklas Meier <dschanoeh@googlemail.com>
 */
public class BusURL implements Serializable {
    private String host;
    private String name;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    private BusURL() {

    }

    /**
     * Build a new BusURL from a String. The String must have the following
     * format: socket://bus\@host:port
     * @param s The String that shall be parsed
     * @return
     */
    public static BusURL fromString(String s) {
        if(s.matches("^socket\\://[a-zA-Z0-9]+\\@[a-zA-Z0-9\\-\\.]+\\:[0-9]+?$")) {
            BusURL b = new BusURL();
            int portSeparator = s.lastIndexOf(':');
            int at = s.indexOf("@");

            b.setPort(Integer.parseInt(s.substring(portSeparator+1)));
            b.setHost(s.substring(at +1, portSeparator));
            b.setName(s.substring(9,at-1));

            return b;
        } else {
            return null;
        }
    }

    public static BusURL fromMap(Map<String, ?> params) {
        BusURL url = new BusURL();
        String name = (String) params.get("name");
        String host = (String) params.get("host");
        Integer port = (Integer) params.get("port");

        url.setName(name);
        url.setHost(host);
        url.setPort(port);

        return url;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.hashCode() == obj.hashCode() && obj instanceof BusURL)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.host != null ? this.host.hashCode() : 0);
        hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 47 * hash + this.port;
        return hash;
    }


    @Override
    public String toString() {
        if(host == null)
            return "";

        if(name != null) {
            return name + " ( socket://" + host + ":" + Integer.toString(port) + " )";
        } else {
            return "socket://" + host + ":" + Integer.toString(port);
        }
    }

}