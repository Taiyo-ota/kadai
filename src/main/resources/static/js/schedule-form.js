/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("scheduleForm");

    if (!form) {
        console.log("予定入力フォームが見つかりません。");
        return;
    }

    form.addEventListener("submit", (event) => {
        const title = document.getElementById("title");
        const date = document.getElementById("date");
        const startTime = document.getElementById("startTime");
        const endTime = document.getElementById("endTime");

        if (!title || !date || !startTime || !endTime) {
            console.error("入力欄のIDを確認してください。");
            return;
        }

        if (
            title.value.trim() === "" ||
            date.value === "" ||
            startTime.value === "" ||
            endTime.value === ""
        ) {
            event.preventDefault();
            alert("予定名、日付、開始時刻、終了時刻を入力してください。");
            return;
        }

        if (endTime.value <= startTime.value) {
            event.preventDefault();
            alert("終了時刻は開始時刻より後に設定してください。");
            return;
        }
    });
});
