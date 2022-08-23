/**
 * @author Maja Devic
 */

var answers = Array();
var correct = Array();
var match_count = 0;
var no_answer_value = '0';

/**
 * Checks if a answer is checked.
 * 
 * @param group
 * @returns {Boolean}
 */
function isChecked(group) {

	for (var i = 0; i < group.length; i++) {
		if (group[i].checked) {
			return true;
		}

	}

	return false;
};

/**
 * Gets the checked answer from element
 * 
 * @param group
 * @param int q
 */
function getAnswer(group, q) {

	// checks first if a answer is checked,if not sets the default value.
	// if a answer is checked it runs through the group elements
	// and takes the value of the checked element
	if (isChecked(group)) {
		for (var i = 0; i < group.length; i++)
			if (group[i].checked) {
				answers.push(group[i].value);
			}

	} else {
		answers.push(no_answer_value);
	}

};

/**
 * Takes the answers of the test 
 * and calculates the result.
 */
function finish() {

	for (var i = 0; i < 5; i++) {
		var el = document.getElementsByName('q' + (i + 1).toString());
		getAnswer(el, i + 1);

	}

	var correct_str = document.getElementById('answers').value;
	var correct = correct_str.split(",");

	
	//compares the correct answers with given answers
	//and calculates than the result
	for (var i = 0; i < 5; i++) {

		if (correct[i] == answers[i]) {
			match_count++;
		}

	}

	var percent_correct = match_count * 20;
	var exam_id = document.getElementById("exam_id").value;

	location.replace("test_res.jsp?percent=" + percent_correct + "&exam_id="
			+ exam_id);

}