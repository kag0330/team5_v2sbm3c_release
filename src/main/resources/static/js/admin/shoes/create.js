document.addEventListener("DOMContentLoaded", function() {
  console.log("DOMContentLoaded event fired");

  var addbutton = document.getElementById('addCategoryButton');
  var create = document.getElementById('create');
  var categoryContainer = document.getElementById('categoryContainer');
  var categoryCount = 1; // 초기 카운트는 1
  var select = categoryContainer.querySelectorAll('.mainCategory');
  var mainCategory = select[select.length - 1];
  var subselect = categoryContainer.querySelectorAll('.subCategory');
  var subCategory = subselect[subselect.length - 1];
  var categorylist = [];
  subCategoryList(mainCategory);


  addbutton.addEventListener('click', function(e) {
    e.preventDefault();

    subselect = categoryContainer.querySelectorAll('.subCategory');
    subCategory = subselect[subselect.length - 1];
    var categoryno = mainCategory.value
    var subcategoryno = subCategory.value;

    if (categoryno.trim() === '' || subcategoryno.trim() === '') {
      alert('신발의 중분류 및 소분류를 선택해주세요.');
      return;
    }
    mainCategory.disabled = true;
    subCategory.disabled = true;

    categorylist.push(parseInt(categoryno, 10));
    fetch('/admin/shoes/addcategory', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(categorylist)
    })
      .then(response => response.json())
      .then(response => {
        var categoryRowHtml = `
                <div class="form-row category-row" id="categoryRow">
                  <div class="form-group col-sm-6">
                    <label for="mainCategory">중분류</label>
                    <select id="mainCategory" name="mainCategory" class="form-control mainCategory">
                      <option value="">중분류를 선택해주세요.</option>
                    </select>
                  </div>
                  <div class="form-group col-sm-6">
                    <label for="subCategory">소분류</label>
                    <select id="subCategory" name="subCategory" class="form-control subCategory">
                      <option value="">소분류를 선택해주세요.</option>
                    </select>
                  </div>
                </div>
            `;

        categoryContainer.insertAdjacentHTML('beforeend', categoryRowHtml);

        select = categoryContainer.querySelectorAll('.mainCategory');
        mainCategory = select[select.length - 1];
        response.name_list.forEach(list => {
          var option = document.createElement('option');
          option.value = list.categoryno;
          option.textContent = list.name;
          mainCategory.appendChild(option);
        });
        subCategoryList(mainCategory);
      })
      .catch(error => console.error('Error:', error));

    if (categoryCount < 4) {
      categoryCount++;
    } else {
      addbutton.style.display = 'none';
    }

  });

  create.addEventListener('click', function(e) {
    e.preventDefault(); // 폼 제출 방지

    var selects = categoryContainer.querySelectorAll('.mainCategory');
    var subselects = categoryContainer.querySelectorAll('.subCategory');

    var mainCategory = selects[selects.length - 1];
    var subCategory = subselects[subselects.length - 1];
    var categoryno = mainCategory.value;
    var subcategoryno = subCategory.value;
    if (categoryno.trim() === '' || subcategoryno.trim() === '') {
      alert('신발의 중분류 및 소분류를 선택해주세요.');
      return;
    }

    var title = document.getElementById('title').value;
    var brand = document.getElementById('brand').value;
    var price = document.getElementById('price').value;
    var contents = document.getElementById('contents').value;
    var visible = document.getElementById('visible').value;
    if (brand.trim() === '' || price.trim() === '' || contents.trim() === '') {
      alert('브랜드, 금액, 내용을 입력해주세요.');
      return;
    }else if(/[^0-9]/.test(price)) {
      alert('올바른 금액을 입력해주세요.');
      return;
    }
    
    var categorylist = [];
    var subcategorylist = [];
    selects.forEach(function(select) {
      if (select.value.trim() !== '') {
        categorylist.push(parseInt(select.value, 10));
      }
    });

    subselects.forEach(function(subselect) {
      if (subselect.value.trim() !== '') {
        subcategorylist.push(parseInt(subselect.value, 10));
      }
    });

    fetch('/admin/shoes/admin_create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        categorylist: categorylist,
        subcategorylist: subcategorylist,
        title:title,
        brand: brand,
        price: parseInt(price, 10),
        contents: contents,
        visible: visible
      })
    })
      .then(response => {
        alert('신발을 생성하였습니다.');
        window.location.href = "/admin/shoes/admin_list_all?now_page=1";
      })
      .catch(error => {
        console.error('신발 생성에 실패했습니다.', error);
        alert('신발 생성에 실패했습니다. 다시 시도해주세요.');
      });
  });
});


function subCategoryList(mainCategory) {

  mainCategory.addEventListener('change', function() {
    var categoryContainer = document.getElementById('categoryContainer');
    subselect = categoryContainer.querySelectorAll('.subCategory');
    subCategory = subselect[subselect.length - 1];
    var mainCategoryName = this.value;
    if (mainCategoryName) {
      fetch('/admin/shoes/select_subname', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          categoryno: parseInt(mainCategoryName, 10)
        })
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('서버에서 소분류 데이터를 불러오지 못했습니다.');
          }
          return response.json();
        })
        .then(data => {
          
          subCategory.innerHTML = '';
          data.subname_list.forEach(list => {
            var option = document.createElement('option');
            option.value = list.categoryno;
            option.textContent = list.subname;
            subCategory.appendChild(option);
          });
        })
        .catch(error => {
          console.error('소분류 데이터를 불러오는 데 실패했습니다.', error);
          alert('소분류 데이터를 불러오는 데 실패했습니다. 다시 시도해주세요.');
        });
    } else {
      subCategory.innerHTML = '<option value="">소분류를 선택해주세요.</option>';
    }
  });
}