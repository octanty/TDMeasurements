<%-- 
    Document   : form
    Created on : Nov 21, 2013, 4:41:49 PM
    Author     : Matej
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<div class="post">
    <table collspace="0" collspan="0">
        <tbody>

            <tr>
                <th><s:label for="b1" name="customer.name"/></th>
                <td><s:text id="b1" name="customer.name"/></td>
            </tr>
            <tr>
                <th><s:label for="b2" name="customer.address"/></th>
                <td><s:text id="b2" name="customer.address"/></td>
            </tr>    
            <tr>
                <th><s:label for="b3" name="customer.username"/></th>
                <td><s:text id="b3" name="customer.userName"/></td>
            </tr>    
            <tr>
                <th><s:label for="b4" name="customer.password"/></th>
                <td><s:password id="b4" name="customer.password"/></td>
            </tr>    
        </tbody>
    </table>
</div>