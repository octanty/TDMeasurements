<%-- 
    Document   : impression_edit
    Created on : 10.11.2013, 20:00:38
    Author     : Mjartan
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<div class="post">
    <table collspace="0" collspan="0">
        <tbody>
            <tr>
                <th><s:label for="b1" name="impression.isbn"/></th>
                <td><s:text id="b1" name="impression.isbn"/></td>
            </tr>
            <tr>
                <th><s:label for="b2" name="impression.author"/></th>
                <td><s:text id="b2" name="impression.author"/></td>
            </tr>    
            <tr>
                <th><s:label for="b3" name="impression.name"/></th>
                <td><s:text id="b3" name="impression.name"/></td>
            </tr>
            <tr>
                <th><s:label for="b4" name="impression.releaseDate"/></th>
                <td><s:text id="b4" name="impression.releaseDate" formatType="cz.muni.fi.pa165.web.DateConverter"/></td>
            </tr>
            <tr>
                <th><s:label for="b5" name="impression.department"/></th>
                <td><s:select id="b5" name="impression.department"><s:options-enumeration enum="cz.muni.fi.pa165.library.dtos.Department"/></s:select></td>
            </tr>
        </tbody>
    </table>
</div>
