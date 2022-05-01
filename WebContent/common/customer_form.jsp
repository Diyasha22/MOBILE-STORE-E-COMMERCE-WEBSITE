<table class="form">
	<tr>
		<td align="right">E-mail:</td>
		<td align="left"><input type="text" id="email" name="email"
			size="45" value="${customer.email}" /></td>
	</tr>
	<tr>
		<td align="right">Full Name:</td>
		<td align="left"><input type="text" id="fullname"
			name="fullname" size="45" value="${customer.fullname}" /></td>
	</tr>
	<tr>
		<td align="right">Password:</td>
		<td align="left"><input type="password" id="password"
			name="password" size="45" value="${customer.password}" /></td>
	</tr>
	<tr>
		<td align="right">Confirm Password:</td>
		<td align="left"><input type="password" id="confirmPassword"
			name="confirmPassword" size="45" value="${customer.password}" /></td>
	</tr>
	<tr>
		<td align="right">Phone Number:</td>
		<td align="left"><input type="text" id="phoneNo" name="phoneNo"
			size="45" value="${customer.phoneNo}" /></td>
	</tr>
	<tr>
		<td align="right">Address:</td>
		<td align="left"><input type="text" id="address" name="address"
			size="45" value="${customer.address}" /></td>
	</tr>
	<tr>
		<td align="right">City:</td>
		<td align="left"><input type="text" id="city" name="city"
			size="45" value="${customer.city}" /></td>
	</tr>
	<tr>
		<td align="right">Zip Code:</td>
		<td align="left"><input type="text" id="zipcode" name="zipcode"
			size="45" value="${customer.zipcode}" /></td>
	</tr>
	<tr>
		<td align="right">Country:</td>
		<td align="left"><input type="text" name="country" id="country" size="45" value="${customer.country}" />
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button type="submit">Save</button>&nbsp;&nbsp;
			<button id="buttonCancel" onclick="history.go(-1);" >Cancel</button>
		</td>
	</tr>
</table>